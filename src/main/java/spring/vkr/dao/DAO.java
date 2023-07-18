package spring.vkr.dao;

import spring.vkr.entity.InitialData;

import java.util.List;

public interface DAO {

    public List<InitialData>showOldInitialData();

    public void createNewData(InitialData initialData);

    public InitialData getOldData(int id);

    public void deleteOldData(int id);
}
