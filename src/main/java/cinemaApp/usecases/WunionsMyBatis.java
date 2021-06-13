package cinemaApp.usecases;
import lombok.Getter;
import lombok.Setter;
import cinemaApp.mybatis.dao.WunionMapper;
import cinemaApp.mybatis.model.Wunion;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class WunionsMyBatis {
    @Inject
    private WunionMapper wunionMapper;

    @Getter
    private List<Wunion> allWunions;

    @Getter @Setter
    private Wunion wunionToCreate = new Wunion();

    @PostConstruct
    public void init() {
        this.loadAllWunions();
    }

    private void loadAllWunions() {
        this.allWunions = wunionMapper.selectAll();
    }

    @Transactional
    public String createWunion() {
        wunionMapper.insert(wunionToCreate);
        return "/myBatis/unions.xhtml?faces-redirect=true";
    }
}
