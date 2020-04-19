package com.sc.service.user;


import com.sc.api.IProducerService;
import com.sc.constant.IProducerServiceUrl;
import com.sc.service.user.dto.UserDTO;
import com.sc.service.user.dto.UserVO;
import com.sc.utils.MapToolsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
/**
 * 存在RestTemplate再注入bean
 */
@ConditionalOnBean(RestTemplate.class)
public class ProducerService implements IProducerService {

   @Autowired
   private RestTemplate restTemplate;

   public List<UserDTO> getUsers() {
      String url = IProducerServiceUrl.PREFIX + IProducerServiceUrl.GETUSERS;
      return (List<UserDTO>) restTemplate.getForObject(url, List.class);
   }

   public UserDTO getUser(Integer id) {
      String url = IProducerServiceUrl.PREFIX + IProducerServiceUrl.GETUSER;
      return restTemplate.getForObject(url, UserDTO.class, id);
   }

   public UserDTO getUserById(UserVO vo) {
      String url = IProducerServiceUrl.PREFIX + IProducerServiceUrl.GETUSERBYID + MapToolsUtil.beanToUrl(vo);
      return restTemplate.getForObject(url, UserDTO.class);
   }


   public UserDTO login(UserVO vo) {
      String url = IProducerServiceUrl.PREFIX + IProducerServiceUrl.LOGIN;
      return restTemplate.postForObject(url, vo, UserDTO.class);
   }
}
