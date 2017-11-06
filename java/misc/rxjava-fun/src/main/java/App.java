import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {

    public static void main(String[] args) {
        reactiveOddNumbersFiltering();
        reactiveFibonacci(10);
    }

    public static void reactiveOddNumbersFiltering() {
        System.out.println("=============");
        System.out.println("Filtrage des nombres pairs dans une liste de nombres entiers");
        Observable.range(0,10).filter(it -> { return it % 2 == 1;}).subscribe(new IntegerSubscriber());
    }

    public static void reactiveFibonacci(int n) {
        final int[] tmp = {0, 0};
        Observable.range(1,n).map( x -> {
            if(x < 3){
                tmp[0] = 1;
                tmp[1] = 1;
                return 1;
            }
            else {
                int item = tmp[0] + tmp[1];
                tmp[0] = tmp[1];
                tmp[1] = item;
                return item;
            }
        }).subscribe(new IntegerSubscriber());
    }
}

class IntegerSubscriber implements Observer<Integer> {
    @Override
    public void onComplete() {
        System.out.println("\nAll work done, sir!");
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("Houston we have a problem : " + e.getMessage());
    }

    @Override
    public void onSubscribe(Disposable d) {
        System.out.println("The subscriber has subscribed!");
    }

    @Override
    public void onNext(Integer item) {
        System.out.print(item + " ");
    }
}