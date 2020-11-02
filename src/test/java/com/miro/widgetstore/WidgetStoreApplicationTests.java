package com.miro.widgetstore;

import static org.assertj.core.api.Assertions.assertThat;

import com.miro.widgetstore.controller.WidgetController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Noble Sebastian
 * @version 1.0
 */
@SpringBootTest
class WidgetStoreApplicationTests {
	@Autowired
	private WidgetController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
