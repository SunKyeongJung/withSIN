package spring.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

/**
 * ThreadLocal 사용법
 * 값 저장: ThreadLocal.set(xxx)
 * 값 조회: ThreadLocal.get()
 * 값 제거: ThreadLocal.remove()
 */
@Slf4j
public class ThreadLocalService {

	private ThreadLocal<String> nameStore = new ThreadLocal<>();

	public String logic(String name) {
		log.info("저장 name={} -> nameStore={}", name, nameStore.get());
		nameStore.set(name);
		sleep(1000);

		log.info("조회 nameStore={}", nameStore.get());
		return nameStore.get();
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
