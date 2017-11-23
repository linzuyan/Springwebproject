package priv.zwc.Service;

import priv.zwc.Module.Role;

import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
public interface IRole {
    public List<Role> queryList();
    public Role queryById(int id);
    public void addRole(Role role);
    public void updateById(Role role);
    public void deleteById(int id);
}
