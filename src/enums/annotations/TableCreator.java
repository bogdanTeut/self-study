package enums.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by bogdan.teut on 26/09/2014.
 */
public class TableCreator {

    static TableDTO createTable(String entity) throws ClassNotFoundException {
        TableDTO tableDTO = null;
        
        Class<?> type = Class.forName(entity);
        DBTable tableAnnotation = type.getAnnotation(DBTable.class);
        if ((tableAnnotation)!= null){
            tableDTO = new TableDTO(tableAnnotation.name());                         
        }

        Field[] fields = type.getDeclaredFields();
        for (Field field:fields){
            Annotation[] annotationFields = field.getDeclaredAnnotations();
            for (Annotation annotation:annotationFields){
                List<FieldDTO> fieldDtoList = new ArrayList<FieldDTO>();
                if (annotation instanceof SQLString){
                    FieldDTO fieldDTO = new FieldDTO(field.getName(), "SQLString");
                    ConstraintDTO constraintDTO = new ConstraintDTO(((SQLString) annotation).constraints().primaryKey(), 
                            ((SQLString) annotation).constraints().allowNull(), 
                            ((SQLString) annotation).constraints().unique());
                    
                    fieldDTO.setConstraints(constraintDTO);
                    fieldDtoList.add(fieldDTO);
                        
                }
                if (annotation instanceof SQLInteger){
                    FieldDTO fieldDTO = new FieldDTO(field.getName(), "SQLInteger");
                    ConstraintDTO constraintDTO = new ConstraintDTO(((SQLInteger) annotation).constraints().primaryKey(),
                            ((SQLInteger) annotation).constraints().allowNull(),
                            ((SQLInteger) annotation).constraints().unique());

                    fieldDTO.setConstraints(constraintDTO);
                    fieldDtoList.add(fieldDTO);
                    
                }
                if (tableDTO.getFields() == null){
                    tableDTO.setFields(fieldDtoList);
                }else{
                    tableDTO.getFields().addAll(fieldDtoList);
                }
            }
        }

        return tableDTO;
    };

    public static void main(String[] args) throws ClassNotFoundException {
        if (args.length == 0){
            System.out.println("No parameter");
            System.exit(0);
        }     
        
        for(String entity:args){
            createTable(entity);
        }
    }
}
