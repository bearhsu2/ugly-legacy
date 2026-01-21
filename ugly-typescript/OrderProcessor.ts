// 典型的「寫成 JavaScript 的 TypeScript」
export class OrderProcessor {

    // 參數使用 any[] 是重構的首要目標
    public process(l: any[], t: string, f: boolean): void {
        let d: number = 0;

        // 1. 過深的巢狀與弱型別存取
        if (l && l.length > 0) {
            for (let i = 0; i < l.length; i++) {
                // 如果 key 打錯 (例如 p 寫成 price)，編譯器不會報錯
                const p = l[i]["p"];
                const q = l[i]["q"];

                if (typeof p === "number" && typeof q === "number") {
                    if (p > 0 && q > 0) {
                        d += p * q;
                    }
                }
            }

            // 2. 難以測試的隨機性 (Math.random)
            if (d > 500) {
                // Math.random() 是副作用，會讓結果不可預測
                if (Math.random() < 0.5) {
                    const rd = 0.01 + (0.1 - 0.01) * Math.random();
                    console.log(`LUCKY! Random discount: ${Math.round(rd * 100)}%`);
                    d = d * (1 - rd);
                }
            }

            // 3. 滿滿的 Magic Strings
            if (t === "VIP") {
                if (d > 1000) {
                    d = d * 0.85;
                } else {
                    d = d * 0.9;
                }
            } else if (t === "GOLD") {
                d = d * 0.95;
            } else {
                if (d > 500) {
                    d = d - 20;
                }
            }

            // 4. 複雜的邏輯門 (VIP 滿 500 免運，其餘加 60)
            if (f) {
                if (!(t === "VIP" && d > 500)) {
                    d = d + 60;
                }
            }

            // 5. 輸出邏輯與計算邏輯耦合
            console.log("------------------------------");
            console.log(`Customer Type: ${t}`);
            console.log(`Total Price: ${d.toFixed(2)}`);

            if (d > 1000) {
                console.log("Status: Large Order");
            } else {
                console.log("Status: Normal Order");
            }
            console.log("------------------------------");

        } else {
            console.log("No items to process.");
        }
    }
}

// 測試用例
const processor = new OrderProcessor();
processor.process([{ p: 100, q: 10 }, { p: 50, q: 2 }], "VIP", true);
