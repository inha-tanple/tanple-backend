package inha.tanple.service;

import inha.tanple.domain.Submission;
import inha.tanple.domain.Product;
import inha.tanple.domain.User;
import inha.tanple.exception.ResourceNotFoundException;
import inha.tanple.repository.SubmissionRepository;
import inha.tanple.repository.ProductRepository;
import inha.tanple.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Submission createSubmission(Long userId, Long productId, String photoUrl) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        Submission submission = new Submission();
        submission.setUser(user);
        submission.setProduct(product);
        submission.setStatus("대기중");
        return submissionRepository.save(submission);
    }

    public Submission getSubmission(Long submissionId) {
        return submissionRepository.findById(submissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found with id " + submissionId));
    }

    public List<Submission> getSubmissionsByUserId(Long userId) {
        return submissionRepository.findByUserId(userId);
    }

    public void submitProduct(Submission submission) {
    }

}
