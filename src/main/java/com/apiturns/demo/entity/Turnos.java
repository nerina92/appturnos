package com.apiturns.demo.entity;

import java.sql.Date;

public class Turnos {
    int turn_id;
    int court_id;
    int user_id;
    Date fecha;
    String hora;
    boolean state;//0=libre, 1=ocupado
    String tipo; //fijo o unico

    public Turnos() {

    }

    public int getTurn_id() {
        return turn_id;
    }

    public void setTurn_id(int turn_id) {
        this.turn_id = turn_id;
    }

    public int getCourt_id() {
        return court_id;
    }

    public void setCourt_id(int court_id) {
        this.court_id = court_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Turnos{" +
                "turn_id=" + turn_id +
                ", court_id=" + court_id +
                ", user_id=" + user_id +
                ", fecha=" + fecha +
                ", hora='" + hora + '\'' +
                ", state=" + state +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
