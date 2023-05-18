package invest.robot.mexc.channel;

//WS通道 被觀察者通用介面
public interface IChannelObservable<T> {
	/**
    * 新增 觀察者
    */
   void add(IChannelObserver<T> observer);

   /**
    * 移除 觀察者
    */
   void remove(IChannelObserver<T> observer);

   /**
    * 通知所有已訂閱的觀察者
    */
   void notifyObservers(T t);

}
