package com.company;

interface UnitsActions {
    public void EnemyDies(Unit unit);
    public boolean PlayerDies();
    public void LvLUp();
    public void MoveTo(Unit target,int id);
    public void MoveFrom(Unit target, int id);
    public void StayOn();
    public void Attack(Unit target);
}
