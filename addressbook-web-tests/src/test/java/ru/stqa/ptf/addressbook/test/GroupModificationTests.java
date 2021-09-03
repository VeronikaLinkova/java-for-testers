package ru.stqa.ptf.addressbook.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

public class GroupModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().GroupsPage();
        if(app.db().groups().size()==0){
            app.group().create(new GroupData().withName("test666"));
        }
        /*
        if (app.group().all().size()==0){
            app.group().create(new GroupData().withName("test666"));
        }
         */
    }
    @Test
    public void testGroupModification(){

        app.goTo().GroupsPage();
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().
                withId(modifiedGroup.getId()).withName("test4").withHeader("tests2").withFooter("test6");

        app.group().modify(group);
        app.goTo().GroupsPage();
        Assert.assertEquals(app.group().count(),before.size());
        Groups after = app.db().groups();

        MatcherAssert.assertThat
                (before.without(modifiedGroup).withAdded(group), CoreMatchers.equalTo(after));
    }
}
