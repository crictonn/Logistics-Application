package com.cherkas.vladimir.logisticstransportations.model;

import com.cherkas.vladimir.logisticstransportations.model.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 30, nullable = false)
    private String first_name;

    @Column(name = "last_name", length = 30)
    private String last_name;

    @Column(name = "username", length = 30, nullable = false, unique = true)
    private String username;

    @Column(name ="email", length = 30, nullable = false, unique = true)
    private String email;

    @Column(name ="password", length = 100, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 15, nullable = false)
    private Roles role;

    @Column(name = "company_id")
    private Long companyID;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Order> userOrders = new ArrayList<>();

    public static User build(User user){
        return new User(
                user.getId(),
                user.getFirst_name(),
                user.getLast_name(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getCompanyID(),
                user.getUserOrders());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
