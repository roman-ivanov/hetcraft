package ua.pp.bizon.test.web;

import ua.pp.bizon.test.bean.BeansEntry;
import ua.pp.bizon.test.bean.FileElementsMock;

public class BeansEntryMock extends BeansEntry {

    public BeansEntryMock() {
    }
    protected void init(){
        setF1fileElements(new FileElementsMock());
        setF2fileElements(new FileElementsMock());
        
    }
}
