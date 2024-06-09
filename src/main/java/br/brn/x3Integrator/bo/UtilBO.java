package br.brn.x3Integrator.bo;

import br.brn.x3Integrator.exception.BussineRuleException;
import br.brn.x3Integrator.exception.ExceptionMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.collection.spi.PersistentBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.lang.reflect.*;
import java.util.*;

@Slf4j
@Component
public class UtilBO {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ExceptionMessage exceptionMessage;

//    public void checkRelationship(Object object, Long idRef) {
//        Metamodel metamodel = entityManager.getMetamodel();
//        Set<EntityType<?>> entities = metamodel.getEntities();
//
//        for (EntityType<?> entityType : entities) {
//            String entityName = entityType.getName();
//
//            if (temCampoCdnCompany(entityType.getJavaType(), object)) {
//                Class<?> repositoryInterface = getRepositoryForEntity(entityName);
//
//                JpaRepository<?, ?> repository = null;
//                // Verifica se encontrou a interface do repositório
//                if (repositoryInterface != null) {
//                    // Agora você pode usar a interface do repositório para o que precisar
//                    // Por exemplo, você pode criar uma instância do repositório usando o applicationContext
//                    repository = (JpaRepository<?, ?>) applicationContext.getBean(repositoryInterface);
//                }
//
//                if (repository instanceof QueryByExampleExecutor) {
//                    QueryByExampleExecutor<Object> queryByExampleExecutor = (QueryByExampleExecutor<Object>) repository;
//                    Example<?> example = criarExemploComCdnCompany(idRef, entityType.getJavaType(), object);
//
//                    Optional<Object> result = queryByExampleExecutor.findOne((Example<Object>) example);
//                    if (Objects.nonNull(result) && !result.isEmpty()) {
//                        // Faça algo se encontrar um registro com cdnCompany = 1L
//                        System.out.println("Encontrado em " + entityName);
//                        throw new BussineRuleException(exceptionMessage.getMessage("deletion.impossible", new Object[]{idRef.toString(),entityName,getIdPropertyValue(result.get())}));
//                    }
//                }
//            }
//        }
//    }

    public void checkRelationship(Object object, String idRef) {
        Metamodel metamodel = entityManager.getMetamodel();
        Set<EntityType<?>> entities = metamodel.getEntities();

        for (EntityType<?> entityType : entities) {
            String entityName = entityType.getName();
            List<String> fieldName = fieldsEntity(entityType.getJavaType(), object);
            for (String field : fieldName) {
                String fieldNameDb = obtainNameFielJoinColumndDb(entityType.getJavaType(), field);
                String tableNameDb = obtainNameTableDb(entityType.getJavaType());
                boolean registerExist = hasRegistryInTable(tableNameDb, fieldNameDb, idRef);
                if (registerExist) {
                    throw new BussineRuleException(exceptionMessage.getMessage("deletion.impossible", new Object[]{idRef.toString(), entityName, field}));
                }
            }

        }
    }

    public void checkRelationship(Object object, Long idRef) {
        Metamodel metamodel = entityManager.getMetamodel();
        Set<EntityType<?>> entities = metamodel.getEntities();

        for (EntityType<?> entityType : entities) {
            String entityName = entityType.getName();
            List<String> fieldName = fieldsEntity(entityType.getJavaType(), object);
            for (String field : fieldName) {
                String fieldNameDb = obtainNameFielJoinColumndDb(entityType.getJavaType(), field);
                String tableNameDb = obtainNameTableDb(entityType.getJavaType());
                boolean registerExist = hasRegistryInTable(tableNameDb, fieldNameDb, idRef);
                if (registerExist) {
                    throw new BussineRuleException(exceptionMessage.getMessage("deletion.impossible", new Object[]{idRef.toString(), entityName, field}));
                }
            }

        }
    }

    private Object getIdPropertyValue(Object result) {
        Class<?> clazz = result.getClass();
        int auxStr = clazz.getName().lastIndexOf(".");
        String nameKey = "cdn" + clazz.getName().substring(auxStr + 1);

        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equals(nameKey)) {
                String fieldName = field.getName();
                String getterMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                try {
                    Method getterMethod = clazz.getMethod(getterMethodName);
                    return getterMethod.invoke(result);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException("Erro ao obter o valor da propriedade com @Id", e);
                }
            }
        }

        return null; // Se nenhum campo com @Id for encontrado
    }

    private ArrayList<String> fieldsEntity(Class<?> entityType, Object object) {
        Field[] campos = entityType.getDeclaredFields();
        ArrayList<String> result = new ArrayList<>();
        for (Field campo : campos) {
            if (campo.getType() == object.getClass()) {
                result.add(campo.getName());
            }
        }
        return result;
    }

    public static String obtainNameFielJoinColumndDb(Class<?> classeEntidade, String nomePropriedade) {
        try {
            Field field = classeEntidade.getDeclaredField(nomePropriedade);

            if (field.isAnnotationPresent(JoinColumn.class)) {
                JoinColumn columnAnnotation = field.getAnnotation(JoinColumn.class);
                return columnAnnotation.name();
            } else {
                // Se a anotação @Column não estiver presente, use o nome da propriedade como padrão
                return nomePropriedade;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String obtainNameTableDb(Class<?> classeEntidade) {
        Set<EntityType<?>> entidades = entityManager.getMetamodel().getEntities();

        for (EntityType<?> entityType : entidades) {
            if (entityType.getJavaType().equals(classeEntidade)) {
                Table tableAnnotation = entityType.getJavaType().getAnnotation(Table.class);
                if (tableAnnotation != null) {
                    return tableAnnotation.name();
                }
            }
        }

        return null; // Não encontrou a entidade no Metamodel
    }

    private boolean hasRegistryInTable(String tabela, String campo, String id) {
        String query = "SELECT COUNT(*) FROM " + tabela + " WHERE " + campo + " = :id";
        Long count = (Long) entityManager.createNativeQuery(query)
                .setParameter("id", id)
                .getSingleResult();

        return count > 0;
    }

    private boolean hasRegistryInTable(String tabela, String campo, Long id) {
        String query = "SELECT COUNT(*) FROM " + tabela + " WHERE " + campo + " = :id";
        Long count = (Long) entityManager.createNativeQuery(query)
                .setParameter("id", id)
                .getSingleResult();

        return count > 0;
    }

//    private boolean temCampoCdnCompany(Class<?> entityType, Object object) {
//        Field[] campos = entityType.getDeclaredFields();
//
//        for (Field campo : campos) {
//            if (campo.getType() == object.getClass()) {
//                return true;
//            }
//        }
//        return false;
//    }

    private Class<?> getRepositoryForEntity(String entityName) {
        Map<String, JpaRepository> repositories = applicationContext.getBeansOfType(JpaRepository.class);

        for (JpaRepository<?, ?> repository : repositories.values()) {

            Class<?>[] interfaces = repository.getClass().getInterfaces();

            for (Class<?> iface : interfaces) {
//                int lastPoint = iface.getName().lastIndexOf(".");
//                if ( iface.getName().substring( lastPoint+1).equals(entityName.concat("Repository"))) {
//                    return repository;
//                }
                Repository repositoryAnnotation = iface.getAnnotation(Repository.class);
                if (Objects.nonNull(repositoryAnnotation)) {
                    String repositoryEntityName = repositoryAnnotation.value();

                    if (repositoryEntityName.equals(entityName.concat("Repository"))) {
                        return iface;
                    }
                }
            }
        }

        return null;
    }

    private String getRepositoryEntityName(JpaRepository<?, ?> repository) {
        Class<?>[] interfaces = repository.getClass().getInterfaces();

        for (Class<?> iface : interfaces) {
            if (iface.getTypeParameters().length == 2) {
                return iface.getTypeParameters()[0].getName();
            }
        }

        throw new IllegalStateException("Não foi possível determinar o nome da entidade para o repositório: " + repository.getClass());
    }

     public void assingObjectToList(Object object, String propertyName) throws IllegalAccessException {

        if (object == null) {
            return;
        }

        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.getType() == List.class) {
                field.setAccessible(true);
                List<?> list = (List<?>) field.get(object);
                if (Objects.nonNull(list)) {
                    for (Object element : list) {
                        for (Field fieldList : element.getClass().getDeclaredFields()) {
                            fieldList.setAccessible(true);
                            if (fieldList.getType() == object.getClass() && fieldList.getName() == propertyName) {
                                fieldList.set(element, object);
                            }
                        }
                    }
                }
            }
        }
    }

    private static Object getValorCampo(Object objeto, Field campo) {
        try {
            // Torne o campo acessível
            campo.setAccessible(true);

            // Obtenha o valor do campo
            return campo.get(objeto);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    private static Set<Class<?>> getSuperClasses(Class<?> clazz) {
        Set<Class<?>> superClasses = new HashSet<>();
        Class<?> superClass = clazz.getClass();
        while (superClass != null) {
            superClasses.add(superClass);
            superClass = superClass.getSuperclass();
        }
        return superClasses;
    }
    public static void entityToDtoCustom(Object source, Object target, List<Class<?>> parentClasses) {
        Field[] campos = target.getClass().getDeclaredFields();
        List<Class<?>> auxClasses = new ArrayList<>(parentClasses);
        if (!parentClasses.contains(target.getClass())) {


            // Adiciona a classe do source à lista de classes de objetos pais visitadas
            parentClasses.add(target.getClass());

            for (Field campo : campos) {
                try {
                    String nomeMetodoGetter = "get" + campo.getName().substring(0, 1).toUpperCase() +
                            campo.getName().substring(1);

                    Method metodoGetter = source.getClass().getMethod(nomeMetodoGetter);
                    Object valor = metodoGetter.invoke(source);

                    if (valor != null && (!campo.getType().getPackage().getName().equals("java.lang") && !campo.getType().getPackage().getName().equals("java.time"))) {

                        String nomeMetodoSetter = "set" + campo.getName().substring(0, 1).toUpperCase() +
                                campo.getName().substring(1);
                        Method metodoSetter = target.getClass().getMethod(nomeMetodoSetter, campo.getType());
                        Type tipoDeCampoGenerico = campo.getGenericType();

                        if (campo.getType().getName().equals("java.util.List") || campo.getType().getName().equals("java.util.ArrayList") ) {
                            List<Object> novaLista = new ArrayList<>();
                            Collection<?> colecao = null;

                            // Verifica se valor é uma instância de Collection (pode ser ArrayList, PersistBag, etc.)
                            if (valor instanceof Collection) {
                                colecao = (Collection<?>) valor;
                            }

                            if (colecao != null) {
                                for (Object aux : colecao) {
                                    Class<?> tipoDosElementos = null;
                                    if (tipoDeCampoGenerico instanceof ParameterizedType) {
                                        ParameterizedType parameterizedType = (ParameterizedType) tipoDeCampoGenerico;
                                        Type[] tipoDeArgumentos = parameterizedType.getActualTypeArguments();

                                        if (tipoDeArgumentos.length > 0) {
                                            // O primeiro argumento é o tipo dos elementos na lista
                                            tipoDosElementos = (Class<?>) tipoDeArgumentos[0];
                                        }
                                    }
                                    Object novoObjeto = tipoDosElementos.getDeclaredConstructor().newInstance();
                                    List<Class<?>> auxClassesList = new ArrayList<>(parentClasses);
                                    entityToDtoCustom(aux, novoObjeto, parentClasses);
                                    parentClasses = auxClassesList;
                                    novaLista.add(novoObjeto);
                                }
                            }
                            metodoSetter.invoke(target, novaLista);
                        } else {
                            Class<?> classeQueDeclarouOCampo = campo.getType();
                            Object novoObjeto = classeQueDeclarouOCampo.getDeclaredConstructor().newInstance();
                            List<Class<?>> auxClassesList = new ArrayList<>(parentClasses);
                            entityToDtoCustom(valor, novoObjeto, parentClasses);
                            parentClasses = auxClassesList;
                            metodoSetter.invoke(target, novoObjeto);
                        }
                    } else {
                        String nomeMetodoSetter = "set" + campo.getName().substring(0, 1).toUpperCase() +
                                campo.getName().substring(1);

                        Method metodoSetter = target.getClass().getMethod(nomeMetodoSetter, campo.getType());

                        metodoSetter.invoke(target, valor);
                    }

                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                    e.printStackTrace();
//            } catch (InstantiationException e) {
//                throw new RuntimeException(e);
                } catch (InstantiationException e) {
//                    throw new RuntimeException(e);
                }
            }
        }
        parentClasses=auxClasses;
    }

    public static Object setNullAllAtributtes(Object o) {

        for (Field f : o.getClass().getDeclaredFields()) {
            if (!java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                f.setAccessible(true);
                try {
                    f.set(o, null);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return o;
    }
}
