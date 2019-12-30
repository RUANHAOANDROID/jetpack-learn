package com.example.jetpack.workmanager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.example.jetpack.R;
import com.example.jetpack.workmanager.adapter.WorkContent;
import com.example.jetpack.workmanager.adapter.WorkContent.DummyItem;
import com.example.jetpack.workmanager.adapter.WorkItemAdapter;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class WorksFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private static final String TAG = "WorksFragment";

    public WorksFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static WorksFragment newInstance(int columnCount) {
        WorksFragment fragment = new WorksFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list2, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new WorkItemAdapter(WorkContent.ITEMS, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            mListener = item -> {
                switch (item.id) {
                    case "1":
                        /**
                         * 一次性任务
                         */
                        OneTimeWorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(TimerWark.class)
                                .build();
                        WorkManager.getInstance(getContext()).enqueue(uploadWorkRequest);
                        /**
                         * 观察执行结果
                         */
                        WorkManager.getInstance(getContext()).getWorkInfoByIdLiveData(uploadWorkRequest.getId())
                                .observe(this, command -> {
                                    Log.d(TAG, "command: ");
                                    if (command.getState()== WorkInfo.State.RUNNING){
                                        //当工作器在活跃地执行时，其处于 RUNNING State。
                                    }
                                    if (command.getState() == WorkInfo.State.SUCCEEDED) {
                                        //只有 OneTimeWorkRequest 可以进入这种 State。表示成功并终止
                                        Toast.makeText(getContext(), "SUCCEEDED : 执行成功", Toast.LENGTH_SHORT).show();
                                    }
                                    if (command.getState() == WorkInfo.State.FAILED) {
                                        //只有 OneTimeWorkRequest 可以进入这种 State。
                                        // 如果工作器返回 Result.failure()，则被视为处于 FAILED 状态。
                                        // 这也是一个终止 State
                                        //所有依赖工作也会被标记为 FAILED，并且不会运行。
                                    }
                                }
                        );
                        break;
                    case "2":
                        //触发条件
                        Constraints constraints = new Constraints.Builder()
                                .setRequiresCharging(true)//要求设备接通电源
                                .build();
                        //PeriodicWorkRequest的正常生命周期为ENQUEUED -> RUNNING -> ENQUEUED。
                        // 根据定义，定期工作不能以成功或失败的状态终止，因为它必须重复进行。
                        // 它只有在明确取消后才能终止。
                        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(TimerWark.class,
                                15, TimeUnit.SECONDS)
                                .setConstraints(constraints)
                                .build();
                        WorkManager.getInstance(getContext()).enqueue(periodicWorkRequest);
                        WorkManager.getInstance(getContext()).getWorkInfoByIdLiveData(periodicWorkRequest.getId())
                                .observe(this,command->{
                                    if (command.getState() == WorkInfo.State.ENQUEUED) {
                                        //满足条件并运行
                                        Toast.makeText(getContext(), "ENQUEUED : 满足运行条件", Toast.LENGTH_SHORT).show();
                                    }
                                    if (command.getState() == WorkInfo.State.BLOCKED){
                                        //如果有尚未完成的前提性工作，则工作处于 BLOCKED State。
                                    }
                                });
                        break;
                }
            };
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
