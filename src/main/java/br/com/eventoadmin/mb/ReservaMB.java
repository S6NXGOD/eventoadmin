package br.com.eventoadmin.mb;

import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.eventoadmin.bo.ReservaBO;
import br.com.eventoadmin.modelo.reserva.Reserva;
import com.xpert.faces.utils.FacesMessageUtils;
import java.util.Calendar;
import java.util.Date;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Joao Pedro
 */
@ManagedBean
@ViewScoped
public class ReservaMB extends AbstractBaseBean<Reserva> implements Serializable {

    @EJB
    private ReservaBO reservaBO;

    private ScheduleModel eventModel;

    @Override
    public void init() {
        carregarAgenda();
    }

    @Override
    public ReservaBO getBO() {
        return reservaBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public void carregarAgenda() {
        eventModel = new DefaultScheduleModel();
        Calendar dtInicial = Calendar.getInstance();
        dtInicial.setTime(new Date());
        dtInicial.set(Calendar.DATE, 1);
        dtInicial.set(Calendar.MONTH, 0);
        dtInicial.set(Calendar.HOUR_OF_DAY, 0);
        dtInicial.set(Calendar.MINUTE, 0);

        Calendar dtFinal = Calendar.getInstance();
        dtFinal.setTime(new Date());
        dtFinal.set(Calendar.DATE, 31);
        dtFinal.set(Calendar.MONTH, 11);
        dtFinal.set(Calendar.HOUR_OF_DAY, 23);
        dtFinal.set(Calendar.MINUTE, 59);
    }

    public void onEventSelect(SelectEvent selectEvent) {
        ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();

        FacesMessageUtils.info(event.getTitle());
    }
    public String getCor(Reserva reserva){
        if(reserva.isPagamento()){
            return "color:green;";
        }else{
            return "color:red;";
        }
    }
}
