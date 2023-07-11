package store.cookshoong.www.cookshoongbackend.store.repository.bank;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import store.cookshoong.www.cookshoongbackend.store.entity.BankType;

/**
 * 은행 타입 레포지토리.
 *
 * @author seungyeon
 * @since 2023.07.04
 */
public interface BankTypeRepository extends JpaRepository<BankType, String>, BankTypeRepositoryCustom {
    /**
     * 은행 이름으로 은행 객체 반환.
     *
     * @param description 은행 이름
     * @return 은행 객체
     */
    Optional<BankType> findByDescription(String description);

    /**
     * 은행이 이미 존재하는지에 대한 여부.
     *
     * @param description 은행 이름
     * @return true : 이미 존재함, false : 아직 존재하지 않음
     */
    boolean existsByDescription(String description);
}
