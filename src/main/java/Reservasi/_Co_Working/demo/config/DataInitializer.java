package Reservasi._Co_Working.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import Reservasi._Co_Working.demo.model.Meja;
import Reservasi._Co_Working.demo.model.Room;
import Reservasi._Co_Working.demo.model.User;
import Reservasi._Co_Working.demo.repository.MejaRepository;
import Reservasi._Co_Working.demo.repository.RoomRepository;
import Reservasi._Co_Working.demo.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final MejaRepository mejaRepository;

    public DataInitializer(UserRepository userRepository, RoomRepository roomRepository,
            MejaRepository mejaRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.mejaRepository = mejaRepository;
    }

    @Override
    public void run(String... args) {
        ensureDemoUser("Admin SpaceBook", "admin@spacebook.com", "admin123", "admin");
        ensureDemoUser("Mahasiswa Demo", "mahasiswa@spacebook.com", "mhs123", "mahasiswa");
        ensureDemoUser("Petugas Demo", "petugas@spacebook.com", "petugas123", "petugas");

        if (roomRepository.count() == 0) {
            Room room = new Room();
            room.setNamaRuangan("Meeting Room A");
            room.setKapasitas(10);
            room.setStatus("tersedia");
            room = roomRepository.save(room);

            mejaRepository.save(createMeja("A1", "tersedia", room));
            mejaRepository.save(createMeja("A2", "tersedia", room));
            mejaRepository.save(createMeja("A3", "tersedia", room));
        }
    }

    private void ensureDemoUser(String nama, String email, String password, String role) {
        userRepository.findByEmail(email).ifPresentOrElse(
                user -> {
                    user.setNama(nama);
                    user.setPassword(password);
                    user.setRole(role);
                    userRepository.save(user);
                },
                () -> userRepository.save(createUser(nama, email, password, role)));
    }

    private User createUser(String nama, String email, String password, String role) {
        User user = new User();
        user.setNama(nama);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }

    private Meja createMeja(String nomor, String status, Room room) {
        Meja meja = new Meja();
        meja.setNomorMeja(nomor);
        meja.setStatus(status);
        meja.setRoom(room);
        return meja;
    }
}
