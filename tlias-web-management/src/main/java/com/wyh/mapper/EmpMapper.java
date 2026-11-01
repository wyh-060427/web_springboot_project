package com.wyh.mapper;

import com.wyh.pojo.Emp;
import com.wyh.pojo.EmpExpr;
import com.wyh.pojo.EmpQueryParam;
import com.wyh.pojo.LoginInfo;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    //--------原始分页逻辑----------
//    @Select("select count(*) from emp e left join dept d on e.dept_id=d.id;")
//    public Long count();
//
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start},#{pageSize};")
//    public List<Emp> list(Integer start, Integer pageSize);


    public List<Emp> list(EmpQueryParam empQueryParam);
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into emp (username, name, gender, phone, job, salary, image, entry_date,dept_id, create_time, update_time)" +
            "VALUES (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId},#{createTime}, #{updateTime});")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    void deleteByEmpIds(List<Integer> empIds);

    @Select("select e.*,d.name deptName from emp e left join tlias.dept d on e.dept_id = d.id where e.id = #{id}")
    Emp searchEmp(Integer id);

    @Select("select * from emp_expr where emp_id = #{id}")
    List<EmpExpr> searchEmpExpr(Integer id);


    void updateById(Emp emp);

    List<Map<String,Object>> countEmpJobData();

    List<Map<String, Object>> countEmpGenderData();
    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
