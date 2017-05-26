#include <jni.h>
#include <stdlib.h>
//压力值是从硬件上面获取到的，这里就模拟一下状态
int getPressure(){
//随机一下，获取随机数的函数莫除以100
	return rand()%100;
}
int flag = 0;
/*
*需要去调用setPressure这个方法
 * Class:     com_xu_jnipressdemo_MainActivity
 * Method:    startMoniter
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_xu_jnipressdemo_MainActivity_startMoniter (JNIEnv * env, jobject obj){
	flag = 1;
    //需要不断的获取压力值，这样才能动态显示
	while(flag){
        //反射太快没有必要，睡1秒
		sleep(1);
			//①拿到字节码对象
		    //找到这个类
			jclass clazz =(*env)->FindClass(env,"com.xu.jnipressdemo.MainActivity");
			//然后在回调这个方法
			jmethodID methodID = (*env)->GetMethodID(env,clazz,"setPressure","(I)V");
			//不用创建对象，就是obj，直接调用，对象，方法，参数值，模拟一下
			(*env)->CallVoidMethod(env,obj,methodID,getPressure());
	}

}

/*
 * Class:     com_xu_jnipressdemo_MainActivity
 * Method:    stopMoniter
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_xu_jnipressdemo_MainActivity_stopMoniter(JNIEnv * env, jobject obj){
	flag = 0;
}
