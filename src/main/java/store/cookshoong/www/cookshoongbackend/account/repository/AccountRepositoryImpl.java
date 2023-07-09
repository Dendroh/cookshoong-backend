package store.cookshoong.www.cookshoongbackend.account.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import store.cookshoong.www.cookshoongbackend.account.entity.QAccount;
import store.cookshoong.www.cookshoongbackend.account.entity.QAccountsStatus;
import store.cookshoong.www.cookshoongbackend.account.entity.QAuthority;
import store.cookshoong.www.cookshoongbackend.account.entity.QRank;
import store.cookshoong.www.cookshoongbackend.account.model.response.QSelectAccountResponseDto;
import store.cookshoong.www.cookshoongbackend.account.model.response.SelectAccountResponseDto;

/**
 * 회원 정보(등급, 권한, 상태를 포함)를 가져오는 Repository 구현.
 *
 * @author koesnam
 * @since 2023.07.08
 */
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<SelectAccountResponseDto> lookupAccount(Long accountId) {
        QAccount account = QAccount.account;
        QAccountsStatus status = QAccountsStatus.accountsStatus;
        QAuthority authority = QAuthority.authority;
        QRank rank = QRank.rank;

        return Optional.ofNullable(jpaQueryFactory.select(new QSelectAccountResponseDto(
                account.id, status.description, authority.description,
                rank.name, account.loginId, account.name,
                account.nickname, account.email, account.birthday,
                account.phoneNumber, account.lastLoginAt))
            .from(account)
            .innerJoin(account.status, status)
            .innerJoin(account.authority, authority)
            .innerJoin(account.rank, rank)
            .where(account.id.eq(accountId))
            .fetchFirst());
    }
}
