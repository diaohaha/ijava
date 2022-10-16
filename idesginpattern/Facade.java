package idesginpattern;

/**
 * 外观模式
 * 隐藏系统的复杂性，并向客户端提供了一个客户端可以访问系统的接口。
 * 
 * 使用场景：
 * 1、为复杂的模块或子系统提供外界访问的模块。 2、子系统相对独立。 3、预防低水平人员带来的风险。
 * 
 * tomcat外层包装了RequestFacade 和 ResponseFacade 代替 Request Response
 * Request、Response 偏底层，且交互复杂，Facade 隐藏了 Request 和 Response
 * 的底层实现细节，降低了底层方法被调用出错的风险
 */

public class Facade {

}
