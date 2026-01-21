 Random();
                if (r.Next(0, 100) < 50) // 50% 機率
                {
                    // 1% ~ 10% 隨機折扣
                    double rd = 0.01 + (0.1 - 0.01) * r.NextDouble();
                    Console.WriteLine($"LUCKY! Random discount: {Math.Round(rd * 100)}%");
                    d = d * (1 - rd);
                }
            }

            // 3. 滿滿的 if-else 與字串比對
            if (t == "VIP")
            {
                if (d > 1000) d *= 0.85;
                else d *= 0.9;
            }
            else if (t == "GOLD")
            {
                d *= 0.95;
            }
            else
            {
                if (d > 500) d -= 20;
            }

            // 4. 複雜的運費邏輯
            if (f)
            {
                if (!(tusing System;
using System.Collections.Generic;
using System.Linq;

public class OrderProcessor
{
    public void Process(List<Dictionary<string, object>> l, string t, bool f)
    {
        double d = 0;

        // 1. 巢狀結構過深 & 糟糕的命名
        if (l != null && l.Count > 0)
        {
            foreach (var i in l)
            {
                // 取得價格與數量 (型別轉換地獄)
                double p = Convert.ToDouble(i["p"]);
                int q = Convert.ToInt32(i["q"]);

                if (p > 0)
                {
                    if (q > 0)
                    {
                        d += p * q;
                    }
                }
            }

            // 2. 難以測試的隨機性 (Random)
            if (d > 500)
            {
                Random r = new == "VIP" && d > 500))
                {
                    d += 60;
                }
            }

            // 5. 輸出邏輯與業務邏輯混在一起
            Console.WriteLine($"Customer Type: {t}");
            Console.WriteLine($"Total Price: {Math.Round(d, 2)}");

            if (d > 1000) Console.WriteLine("Status: Large Order");
            else Console.WriteLine("Status: Normal Order");
        }
        else
        {
            Console.WriteLine("No items to process.");
        }
    }
}
