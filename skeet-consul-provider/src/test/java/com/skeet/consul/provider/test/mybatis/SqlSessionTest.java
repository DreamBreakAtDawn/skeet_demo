package com.skeet.consul.provider.test.mybatis;

import com.skeet.consul.provider.project.order.dao.mapper.InventorySnapshotMapper;
import com.skeet.consul.provider.project.order.model.domain.InventorySnapshotCriteria;
import com.skeet.consul.provider.project.order.model.export.InventoryChargeExportDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/6/19 9:06
 */
public class SqlSessionTest {

    public static void main(String[] args) throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("META-INF/mybatis/mapper/order/InventorySnapshotMapper.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = factory.openSession();
        InventorySnapshotMapper mapper = sqlSession.getMapper(InventorySnapshotMapper.class);
        List<InventoryChargeExportDTO> inventoryChargeExportDTOS = mapper.selectChargeData(InventorySnapshotCriteria.builder().build());
    }
}
