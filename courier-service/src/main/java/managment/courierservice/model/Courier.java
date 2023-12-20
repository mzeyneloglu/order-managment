package managment.courierservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MN_COURIER")
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "COURIER_NAME")
    private String courierName;
    @Column(name = "COURIER_SURNAME")
    private String courierSurname;
    @Column(name = "COURIER_PHONE")
    private String courierPhone;
    @Column(name = "COURIER_COMPANY")
    private String courierCompany;

}
