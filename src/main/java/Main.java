import com.slb.backtest.Backtest;
import com.slb.data.UsdJpy;
import com.slb.strategy.MeanReversal1;

public class Main {

    public static void main(String[] args) {
        new Backtest(new UsdJpy(5), new MeanReversal1());
    }
}
