package cn.yahoo.asxhl2007.avh;

import android.view.LayoutInflater;
import android.view.View;

/**
 * 使用时请注意View类型
 * 
 * @author yangcheng
 * 
 */
public class AutomaticViewHolder {

    protected View root;

    public AutomaticViewHolder(View root) {
        this.root = root;
        if (root == null) {
            throw new NullPointerException( "参数root不能为空" );
        }
        init();
    }

    public AutomaticViewHolder(LayoutInflater inflater , int layout) {
        root = inflater.inflate( layout, null );
        if (root == null) {
            throw new NullPointerException( "指定的layout(id:" + layout + ")不存在" );
        }
        init();
    }

    public View getRootView() {
        return root;
    }

    @SuppressWarnings("unchecked")
    public <V extends View> V findViewById(int id) {
        return (V) root.findViewById( id );
    }

    @SuppressWarnings("unchecked")
    public <V extends View> V findViewById(View parent, int id) {
        return (V) parent.findViewById( id );
    }

    private void init() {

        AutomaticViewHolderUtil.findAllViews( this, root );
    }

//    /**
//     * 使用Res标记字段应确保使用正确的类型，必须是View或其子类型，并且确保layout文件使用的控件是该字段的类型或该字段的子类型！
//     * <p>
//     * 字段名与id名相同时，可以省略id（推荐不要省略id，以方便重构时变更id）
//     * 
//     * @author yangcheng
//     * 
//     */
//    @Documented
//    @Retention(RetentionPolicy.RUNTIME)
//    @Target(ElementType.FIELD)
//    public @interface Res {
//        public int value() default -1;
//    }

    @SuppressWarnings("serial")
    public static class IllegalViewHolderException extends RuntimeException {

        IllegalViewHolderException(String msg) {
            super( msg );
        }

        IllegalViewHolderException() {
            super( "使用Res标记字段应确保使用正确的类型，必须是View或其子类型，并且确保layout文件使用的控件是该字段的类型或该字段的子类型！" );
        }
    }
}
