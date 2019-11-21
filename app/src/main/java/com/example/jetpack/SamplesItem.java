package com.example.jetpack;

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
public class SamplesItem {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Integer, DummyItem> ITEM_MAP = new HashMap<Integer, DummyItem>();


    static {
        addItem(new DummyItem(1, "DataBingding", "DataBingding学习"));
        addItem(new DummyItem(2, "ViewModule", "ViewModule"));
        addItem(new DummyItem(3, "navigation safe args (安全传值)", "navigation safe args"));
        addItem(new DummyItem(4, "navigation 深层链接(待在Notification 实现)", "navigation"));
        addItem(new DummyItem(5, "ViewModule", "ViewModule学习"));
        addItem(new DummyItem(6, "ViewModule", "ViewModule学习"));
        addItem(new DummyItem(7, "ViewModule", "ViewModule学习"));
        addItem(new DummyItem(8, "ViewModule", "ViewModule学习"));
        addItem(new DummyItem(9, "ViewModule", "ViewModule学习"));

    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }


    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final int id;
        public final String content;
        public final String details;

        public DummyItem(int id, String content, String details) {
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
