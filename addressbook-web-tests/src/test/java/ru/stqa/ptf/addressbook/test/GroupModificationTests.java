package ru.stqa.ptf.addressbook.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().GroupsPage();
        if (app.group().all().size()==0){
            app.group().create(new GroupData().withName("test1"));
        }
    }
    @Test
    public void testGroupModification(){

        //int before = app.getGroupHelper().getGroupsCount();
        app.goTo().GroupsPage();
        Groups before = app.group().all();
        //Set<GroupData> before = app.group().all();
        //List<GroupData> before = app.group().list();
        GroupData modifiedGroup = before.iterator().next();
        //int index = before.size()-1;
        GroupData group = new GroupData().
                withId(modifiedGroup.getId()).withName("test4").withHeader("tests2").withFooter("test6");

        app.group().modify(group);

        app.goTo().GroupsPage();
        //int after = app.getGroupHelper().getGroupsCount();
        Groups after = app.group().all();
        //Set<GroupData> after = app.group().all();
        //List<GroupData> after = app.group().list();
        //Assert.assertEquals(after,before);
        //Assert.assertEquals(after.size(),before.size());
        //before.remove(modifiedGroup);
        //before.add(group);
        //Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
        //Comparator<? super GroupData> byId = (g1,g2) ->Integer.compare(g1.getId(),g2.getId());
        //before.sort(byId);
        //after.sort(byId);
        //Assert.assertEquals(before, after);
        MatcherAssert.assertThat
                (before.without(modifiedGroup).withAdded(group), CoreMatchers.equalTo(after));
    }
}
