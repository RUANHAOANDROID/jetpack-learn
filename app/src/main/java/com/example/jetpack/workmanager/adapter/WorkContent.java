package com.example.jetpack.workmanager.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class WorkContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();


    static {
        // Add some sample items.
        addItem(new DummyItem("1", "Worker 基本（一次性）", "基本实现"));
        addItem(new DummyItem("2", "Worker 基本（周期）", "基本实现"));
        addItem(new DummyItem("3", "CoroutineWorker Kotlin用户实现", "基本实现"));
        addItem(new DummyItem("4", " RxWorker RxJava", "基本实现"));
        addItem(new DummyItem("5", " ListenableWorker 自定义", "基本实现"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
