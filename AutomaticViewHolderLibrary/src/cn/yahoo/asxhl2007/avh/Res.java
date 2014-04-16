package cn.yahoo.asxhl2007.avh;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 使用Res标记字段应确保使用正确的类型，必须是View或其子类型，并且确保layout文件使用的控件是该字段的类型或该字段的子类型！
 * <p>
 * 字段名与id名相同时，可以省略id（推荐不要省略id，以方便重构时变更id）
 * 
 * @author yangcheng
 * 
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Res {
    public int value() default -1;
}
