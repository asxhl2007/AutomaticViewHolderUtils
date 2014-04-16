package cn.yahoo.asxhl2007.avh;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public class AutomaticViewHolderUtil {

    /**
     * 帮助开发者查找所有在ViewHolder中拥有Res注释的View(会向上递归查找，直到Activity,Object,或是AutomaticViewHolder时停止）.
     * 
     * @param viewHolder 用于保持View引用的对象
     * @param rootView Holder中所有View的共同根View
     */
    public static void findAllViews(Object viewHolder, View rootView) {
        if (rootView == null) {
            throw new NullPointerException( "参数rootView不能为空." );
        }

        Class<?> clazz = viewHolder.getClass();
        Class<?> r_id_class = AutomaticViewHolderUtil.getR_ID_Class( rootView.getContext() );
        
        while (clazz != Activity.class && clazz != AutomaticViewHolder.class && clazz != Object.class) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Res res = field.getAnnotation( Res.class );
                if (res != null) {
                    if (View.class.isAssignableFrom( field.getType() )) {

                        try {
                            int id = res.value();
                            if (id == -1) {
                                Field idField = null;
                                try {
                                    idField = r_id_class.getField( field.getName() );
                                } catch (NoSuchFieldException e) {
                                    throw new IllegalViewTypeException( "您没有定义名为'" + field.getName() +
                                        "'的id，你可以指定一个id（推荐）或使用与id相同的字段名." );
                                }
                                idField.setAccessible( true );
                                id = idField.getInt( null );
                            }

                            View view = findViewById( rootView, id );
                            if (view == null) {
                                if(id == -1){
                                    throw new IllegalViewTypeException( "该layout中不存在名为'" + field.getName() +
                                        "'的id，请检查layout中的id." );
                                }else{
                                    throw new IllegalViewTypeException( "该layout中不存在id'" + res.value() + "'(" +
                                        field.getName() + ")，请检查layout中的id." );
                                }
                            }

                            field.setAccessible( true );
                            try {
                                field.set( viewHolder, view );
                            } catch (IllegalArgumentException e) {
                                throw new IllegalViewTypeException( "字段类型不匹配 :" + field.getName() + "  字段声明类型：" +
                                    field.getType().getName() + ",  layout定义类型：" + view.getClass() );
                            }
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                            throw new IllegalViewTypeException("请勿将以Res注释的字段声明为final.");
                        }
                    } else {
                        throw new IllegalViewTypeException();
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
    }
    
    @SuppressWarnings("unchecked")
    public static <V> V findViewById(View parent, int id){
        return (V) parent.findViewById( id );
    }
    
    public static Class<?> getR_ID_Class(Context context){
        try {
            return Class.forName( context.getPackageName() + ".R$id" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
