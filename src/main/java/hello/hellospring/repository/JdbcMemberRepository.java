package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import javax.sql.StatementEvent;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository{

    private final DataSource dataSource;

    @Autowired
    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    final String saveSql = "insert into member(name) values(?)";
    final String findIdSql = "select * from member where id = ?";
    final String findNameSql = "select * from member where name = ?";
    final String findAllSql = "select * from member";

    @Override
    public Member save(Member member) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(saveSql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,member.getName());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if(rs.next()){
                member.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }

            return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findById(Long id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(findIdSql);
            pstmt.setLong(1,id);

            rs = pstmt.executeQuery();

            if(rs.next()){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findByName(String name) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(findNameSql);
            pstmt.setString(1,name);

            rs = pstmt.executeQuery();

            if(rs.next()){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Member> findAll() {
        Connection conn=null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(findAllSql);
            rs = pstmt.executeQuery();

            if(rs.next()){

            }
        } catch (Exception e){
            throw new IllegalStateException("멤버 정보를 찾을 수 없습니다.");
        } finally {
            close(conn,pstmt,rs);
        }
    }


    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
    {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
