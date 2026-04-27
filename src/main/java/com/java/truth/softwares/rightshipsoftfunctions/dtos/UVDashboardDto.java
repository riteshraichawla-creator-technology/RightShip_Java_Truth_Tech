package com.java.truth.softwares.rightshipsoftfunctions.dtos;

import com.java.truth.softwares.rightshipsoftfunctions.dtos.UVNotificationDto;
import com.java.truth.softwares.rightshipsoftfunctions.dtos.UVOceanWavesGeneralUniversalDTO;
import com.java.truth.softwares.rightshipsoftfunctions.dtos.UVOrderDto;

import java.util.List;

public class UVDashboardDto {

    private UVUserDto user;
    private List<UVOrderDto> orders;
    private List<UVNotificationDto> notifications;
    private UVOceanWavesGeneralUniversalDTO ultraVelocityGeneralUniversalDTO;

    public UVDashboardDto(UVUserDto user,
                          List<UVOrderDto> orders,
                          List<UVNotificationDto> notifications) {
        this.user = user;
        this.orders = orders;
        this.notifications = notifications;
    }

    public UVUserDto getUser() {
        return user;
    }

    public void setUser(UVUserDto user) {
        this.user = user;
    }

    public List<UVOrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<UVOrderDto> orders) {
        this.orders = orders;
    }

    public List<UVNotificationDto> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<UVNotificationDto> notifications) {
        this.notifications = notifications;
    }

    public UVOceanWavesGeneralUniversalDTO getUVOceanWavesGeneralUniversalDTO() {
        return ultraVelocityGeneralUniversalDTO;
    }

    public void setUVOceanWavesGeneralUniversalDTO(UVOceanWavesGeneralUniversalDTO ultraVelocityGeneralUniversalDTO) {
        this.ultraVelocityGeneralUniversalDTO = ultraVelocityGeneralUniversalDTO;
    }
}
