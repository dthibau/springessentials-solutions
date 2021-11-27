package org.formation.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.formation.model.Member;
import org.formation.model.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<Member> optMember = memberRepository.findByEmail(login);
        if ( !optMember.isPresent() ) 
        	throw new UsernameNotFoundException("Invalides login/mot de passe");
        Member member = optMember.get();
        Set<GrantedAuthority> grantedAuthorities= new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("FRONTEND"));

        return new User(member.getEmail(), "{noop}"+member.getPassword(), grantedAuthorities);
	}

}
