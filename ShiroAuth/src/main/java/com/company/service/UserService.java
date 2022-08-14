package com.company.service;

import com.company.model.Permission;
import com.company.model.Role;
import com.company.model.User;
import lombok.Data;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
@Data
public class UserService {
    private Role viewRole=new Role("viewRole",new ArrayList<Permission>(){{
            add(Permission.VIEW);
        }});
    private Role norRole=new Role("norRole",new ArrayList<Permission>(){{
        add(Permission.VIEW);
        add(Permission.ADD);
        add(Permission.DELETE);
        add(Permission.EDIT);
    }});
    private Role adminRole=new Role("adminRole",new ArrayList<Permission>(){{
        add(Permission.VIEW);
        add(Permission.ADD);
        add(Permission.DELETE);
        add(Permission.EDIT);
        add(Permission.OTHER);
        add(Permission.ADMIN);
    }});

    private User adminUser=new User("admin",new Md5Hash("admin-pwd").toString(), Arrays.asList(adminRole));

    private User normalUser=new User("normal",new Md5Hash("normal-pwd").toString(),Arrays.asList(norRole));

    private User viewUser=new User("view",new Md5Hash("view").toString(),Arrays.asList(viewRole));

    public User getUser(String name){
        User user;
        switch (name){
            case "admin":
                user=getAdminUser();
                break;
            case "normal":
                user=getNormalUser();
            case "view":
                user=getViewUser();
            default:
                user=null;
        }
        return user;
    }

    public void addPermission(String userName,Role role){
        getUser(userName).getRoles().add(role);
    }

}
