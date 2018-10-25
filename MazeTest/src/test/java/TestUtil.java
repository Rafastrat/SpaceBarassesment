import components.Coordinate;
import components.CoordinateStatus;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class TestUtil {

    public static Object getField(Object target, String name, Class<?> type) {
        Field field = findField(target.getClass(), name, type);
        makeAccessible(field);
        return getField(field, target);
    }

    public static Field findField(Class<?> clazz, String name, Class<?> type) {
        Class<?> searchType = clazz;
        while (!Object.class.equals(searchType) && searchType != null) {
            Field[] fields = searchType.getDeclaredFields();
            for (Field field : fields) {
                if ((name == null || name.equals(field.getName())) && (type == null || type.equals(field.getType()))) {
                    return field;
                }
            }
            searchType = searchType.getSuperclass();
        }
        return null;
    }

    public static Object getField(Field field, Object target) {
        try {
            return field.get(target);
        } catch (IllegalAccessException ex) {

        }
        return null;
    }

    public static void makeAccessible(Field field) {
        if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) ||
                Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
            field.setAccessible(true);
        }
    }

    public static void setExitSquare(Coordinate[][] coords, Coordinate goalPoint, int x, int y, CoordinateStatus status) {
        int yLength = coords[0].length - 1;
        if (x < 0 || x > coords.length - 1 || y < 0 || y > yLength) {
            return; // does nothing.
        }

        int xPoint = goalPoint.getRow();
        int yPoint = goalPoint.getColumn();

        //replacing existing square with a wall.
        coords[xPoint][yPoint] = new Coordinate(xPoint, yPoint, status);

        //setting the new x and y the new exit point.
        coords[x][y] = new Coordinate(x, y, 'F');
        System.out.println(coords);
    }

    public static void setSquare(Coordinate[][] squares, int x, int y, CoordinateStatus status) {
        squares[x][y] = new Coordinate(x, y, status);
    }
}