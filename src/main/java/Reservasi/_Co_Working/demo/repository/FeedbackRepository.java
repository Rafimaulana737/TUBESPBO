package Reservasi._Co_Working.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Reservasi._Co_Working.demo.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
