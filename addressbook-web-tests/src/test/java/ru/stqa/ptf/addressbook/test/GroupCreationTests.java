package ru.stqa.ptf.addressbook.test;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line!=null){
      xml+=line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupData.class);
    List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
    return groups.stream().map((g)->new Object[]{g}).collect(Collectors.toList()).iterator();
  }
  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
    String json = "";
    String line = reader.readLine();
    while (line!=null){
      json+=line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
    return groups.stream().map((g)->new Object[]{g}).collect(Collectors.toList()).iterator();
  }
  @Test(dataProvider = "validGroupsFromJson")
  public void testGroupCreation(GroupData group) throws Exception {

    app.goTo().GroupsPage();
    //Set<GroupData> before = app.group().all();
    Groups before = app.group().all();

    List<GroupData> beforeList = app.group().list();
    //GroupData group = new GroupData().withName("test1");
    //int before = app.getGroupHelper().getGroupsCount();

    app.group().create(group);
    //первоначальный вариант создания группы
    //app.getGroupHelper().initGroupCreation();
    //app.getGroupHelper().fillGroupForm(group);
    //app.getGroupHelper().submitGroupCreation();
    app.goTo().GroupsPage();
    //Set<GroupData> after = app.group().all();
    Groups after = app.group().all();
    //List<GroupData> after = app.group().list();

    //int after = app.getGroupHelper().getGroupsCount();
    //app.logOut();
    //Assert.assertEquals(after,before + 1);
    //Assert.assertEquals(after.size(),before.size() + 1);
    assertThat(after.size(), equalTo(before.size() + 1));
    //after.
    /*
    int max = 0;
    for (GroupData g: after){
      if (g.getId()>max){
        max = g.getId();
      }
    }
     */

    //int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId();
    //before.add(group.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()));

    //group.setId(max);
    //закомментирую компоратор, т.к. он относится к сортировке для списков,а мы перешли на множества
    /*
    Comparator<? super GroupData> byId = (g1, g2) ->Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
     */
    //Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    //Assert.assertEquals(before,after);
    assertThat(after,
            equalTo(before.withAdded(group.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));
  }
  @Test
  public void testBadGroupCreation() throws Exception {

    app.goTo().GroupsPage();
    Groups before = app.group().all();

    List<GroupData> beforeList = app.group().list();
    GroupData group = new GroupData().withName("test1'");

    app.group().create(group);
    app.goTo().GroupsPage();
    assertThat( app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }
}
