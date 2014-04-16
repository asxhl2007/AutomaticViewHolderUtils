package com.example.automaticviewholderexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.yahoo.asxhl2007.avh.AutomaticViewHolder;
import cn.yahoo.asxhl2007.avh.AutomaticViewHolderUtil;
import cn.yahoo.asxhl2007.avh.Res;

public class MainActivity extends Activity {

    @Res()
    private TextView txtHello;
    @Res(R.id.imgA)
    private ImageView imageAAA;

    @Res()
    private View testContainer;
    
    private ViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        
        AutomaticViewHolderUtil.findAllViews( this, findViewById( R.id.root ) );
        
        txtHello.setText( "Hello!" );
        imageAAA.setImageResource( android.R.drawable.ic_media_play );
        
        viewHolder = new ViewHolder( testContainer );
        viewHolder.txtLabel_XXX.setText( "XXX" );
        viewHolder.imgB.setImageResource( android.R.drawable.ic_dialog_alert );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }

    class ViewHolder extends AutomaticViewHolder {

        @Res(R.id.txtLabel)
        private TextView txtLabel_XXX;
        @Res()
        private ImageView imgB;

        public ViewHolder(View root) {
            super( root );
        }

    }
}
