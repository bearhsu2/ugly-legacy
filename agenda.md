# 當 Legacy Code 需要修改時 - Kuma 的遺留程式碼實戰工作坊（首波試辦場）

# 時間
- 10:00 ~ 12:00
- 13:00 ~ 16:00
# 流程
- 開場
    - 寫下你的問題
- Lab 1：Greetings
- 講解與示範
- 淺談測試
    - 測回傳
    - 測狀態
    - 測互動
- Lab 2：Ugly Sample
- 講解與示範
- Q&A
- 回饋問卷

# Sample Code
``` java
import java.util.*;

public class OrderProcessor {

    public void process(List<Map<String, Object>> l, String t, boolean f) {
        double d = 0;

        if (l != null && l.size() > 0) {
            for (Map<String, Object> i : l) {
                // 取得價格
                double p = (double) i.get("p");
                // 取得數量
                int q = (int) i.get("q");

                if (p > 0) {
                    if (q > 0) {
                        d += p * q;
                    }
                }
            }

            // 根據客戶類型給折扣
            if (t.equals("VIP")) {
                if (d > 1000) {
                    d = d * 0.85;
                } else {
                    d = d * 0.9;
                }
            } else if (t.equals("GOLD")) {
                d = d * 0.95;
            } else {
                if (d > 500) {
                    d = d - 20;
                }
            }

            // 運費邏輯
            if (f) {
                if (t.equals("VIP") && d > 500) {
                    // VIP 滿 500 免運
                } else {
                    d = d + 60;
                }
            }

            // 輸出結果
            System.out.println("Customer Type: " + t);
            System.out.println("Total Price: " + Math.round(d * 100.0) / 100.0);
            
            if (d > 1000) {
                System.out.println("Status: Large Order");
            } else {
                System.out.println("Status: Normal Order");
            }
        } else {
            System.out.println("No items to process.");
        }
    }

    public static void main(String[] args) {
        // 測試用例
        List<Map<String, Object>> items = new ArrayList<>();
        Map<String, Object> item1 = new HashMap<>();
        item1.put("p", 100.0);
        item1.put("q", 5);
        items.add(item1);

        OrderProcessor op = new OrderProcessor();
        op.process(items, "VIP", true);
    }
}
```

# Reference
