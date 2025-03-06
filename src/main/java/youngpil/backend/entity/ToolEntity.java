package youngpil.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import youngpil.backend.dto.request.tool.PatchToolRequestDto;
import youngpil.backend.dto.request.tool.PostToolRequestDto;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "tools")
@Table(name = "tools")
public class ToolEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer toolNumber;
    private String name;
    private String purpose;
    private Integer count;

    public ToolEntity(PostToolRequestDto dto) {
        this.name = dto.getName();
        this.purpose = dto.getPurpose();
        this.count = dto.getCount();
    }

    public void patch(PatchToolRequestDto dto) {
        this.name = dto.getName();
        this.purpose = dto.getPurpose();
        this.count = dto.getCount();
    }
}
