package invest.robot.mexc.channel;

//WS通道觀察者通用介面
public interface IChannelObserver<T> {
	String getChannelName();
	void update(T t);
}
