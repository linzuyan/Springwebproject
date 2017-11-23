package priv.zwc.ServiceImp;

import priv.zwc.Service.IRole;
import priv.zwc.Mapper.RoleMapper;
import priv.zwc.Module.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
@Service
public class RoleService implements IRole {
    @Autowired
    private RoleMapper roleMapper;
    public List<Role> queryList() {
        return roleMapper.queryList();
    }

    public Role queryById(int id) {
        return roleMapper.queryById(id);
    }

    public void addRole(Role role) {
        roleMapper.addRole(role);
    }

    public void updateById(Role role) {
        roleMapper.updateById(role);
    }

    public void deleteById(int id) {
        roleMapper.deleteById(id);
    }
}
