package ru.stqa.ptf.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase{
    @Test
    public void testGroupModification(){
        app.getNavigationHelper().goToGroupsPage();
        if (!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        //int before = app.getGroupHelper().getGroupsCount();
        app.getNavigationHelper().goToGroupsPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size()-1);
        app.getGroupHelper().initGroupModification();
        GroupData group = new GroupData(before.get(before.size()-1).getId(),"test4", "tets2", "test6");
        app.getGroupHelper().fillGroupForm(new GroupData("test4", "tets2", "test6"));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().goToGroupsPage();
        //int after = app.getGroupHelper().getGroupsCount();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        //Assert.assertEquals(after,before);
        Assert.assertEquals(after.size(),before.size());
        before.remove(before.size()-1);
        before.add(group);
        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
        Comparator<? super GroupData> byId = (g1,g2) ->Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
