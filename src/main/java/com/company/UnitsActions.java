package com.company;

interface UnitsActions {
    public void EnemyDies(Unit unit);
    public boolean PlayerDies();
    public void LvLUp();
    public void MoveTo(Unit target,int id);
    public void MoveFrom(Unit target, int id);
    public void StayOn();
    public void Attack(Unit target, int id);
    public void UseItem();
    public void DropItem();
    public void ReplaceItem(Item item);
}
