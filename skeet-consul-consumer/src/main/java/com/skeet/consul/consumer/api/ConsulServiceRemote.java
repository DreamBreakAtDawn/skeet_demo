package com.skeet.consul.consumer.api;

import com.skeet.common.api.service.ConsulWsService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/3/31 17:06
 */
@FeignClient("consul-provider")
public interface ConsulServiceRemote extends ConsulWsService {
}
