package com.example.jetpack;

import android.app.PendingIntent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.NavDirections;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.jetpack.navigation.DeepLinkFragment;
import com.example.jetpack.navigation.SafeArgsFragment;
import com.example.jetpack.navigation.SafeArgsFragmentArgs;
import com.example.jetpack.navigation.User;
import com.example.jetpack.utlis.Logger;

/**
 * 主页面
 */
public class MainNavigationActivity extends AppCompatActivity implements SamplesListFragment.OnListFragmentInteractionListener {
    private String TAG = "MainNavigationActivity";
    private NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
         navHostFragment = (NavHostFragment) getSupportFragmentManager()
                 .findFragmentById(R.id.nav_host_fragment);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, SamplesListFragment.newInstance(1))
//                    .commitNow();
//        }

    }

    @Override
    public void onListFragmentInteraction(View view,SamplesItem.DummyItem item) {
        Log.d(TAG, "onListFragmentInteraction: ");

        //Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.nav_mainFragment);
        switch (item.id){
            case 1://dataBinding基本使用
                Bundle bundle =new Bundle();
                bundle.putString("key",item.content);
                getNavigate().navigate(R.id.action_nav_itemFragment_to_dataBindingFragment,bundle);//通过budle传值
                break;
            case 2://ViewModule的基本使用
                Bundle bundle2 =new Bundle();
                bundle2.putString("key1",item.content);
                getNavigate().navigate(R.id.ViewModuleFragment,bundle2);//直接通过id跳转并非action
                break;
            case 3://navigation 安全传递参数
                SafeArgsFragmentArgs args =new SafeArgsFragmentArgs.Builder("张三",18,new User("张三",18,"男")).build();
                Navigation.findNavController(view).navigate(R.id.safeArgsFragment,args.toBundle());
                break;
            case 4://深层链接 还未实现，在Notification中实现PendingIntent 跳转深层链接
                PendingIntent pendingIntent = new NavDeepLinkBuilder(getApplicationContext())
                        .setGraph(R.navigation.nav_graph)
                        .setDestination(R.id.deepLinkFragment)
                        //.setArguments(args)
                        .createPendingIntent();
                Toast.makeText(this, "Notification中实现PendingIntent 跳转深层链接、待实现", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Navigation.findNavController(view).navigate(R.id.locationFragment);
                break;
            case 6:
                getNavigate().navigate(R.id.worksFragment);
                break;

        }
    }
   private NavController  getNavigate(){
        return navHostFragment.getNavController();
    }
}
