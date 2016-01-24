/*CS350
 *Project #5
 *Andrew Pan
 *This class defines driver and stores all information for individual drivers, as well as
 *returns the information in a String.
 */
public class CDriver {
	public int num, years, transmission;
	public String name;
	public boolean jeep;
	public boolean[] model = new boolean[5];
	public boolean newd;
	public CDriver(int n)
	{
		num = n;
		years = -1;
		transmission = 0;
		name = "";
		jeep = false;
		for(int x = 0; x < 5; x++)
			model[x] = false;
		newd = true;
	}
	public CDriver(int n, String nm, int y, boolean j, boolean compass, boolean gc, boolean patriot, boolean renegade, boolean other, int t)
	{
		num = n;
		name = nm;
		years = y;
		jeep = j;
		model[0] = compass;
		model[1] = gc;
		model[2] = patriot;
		model[3] = renegade;
		model[4] = other;
		transmission = t;
		newd = false;
	}
	public int getNum()
	{
		return num;
	}
	public void editName(String nm)
	{
		name = nm;
	}
	public void editYears(int y)
	{
		years = y;
	}
	public void editJeep(boolean j)
	{
		jeep = j;
	}
	public void editModel(boolean compass, boolean gc, boolean patriot, boolean renegade, boolean other)
	{
		model[0] = compass;
		model[1] = gc;
		model[2] = patriot;
		model[3] = renegade;
		model[4] = other;
	}
	public void editTransmission(int t)
	{
		transmission = t;
	}
	public String line()
	{
		String h = Integer.toString(num);
		String s = "";
		for(int x = 5; x > h.length(); x--)
		{
			s += "0";
		}
		s += num + "            " + name;
		for(int x = name.length(); x < 20; x++)
		{
			s += " ";
		}
		s += years;
		for(int x = Integer.toString(years).length(); x < 17; x++)
		{
			s += " ";
		}
		if(jeep)
			s += "Yes             ";
		else
			s += "No              ";
		if(model[0])
			s += "C";
		else
			s += "-";
		if(model[1])
			s += "G";
		else
			s += "-";
		if(model[2])
			s += "P";
		else
			s += "-";
		if(model[3])
			s += "R";
		else
			s += "-";
		if(model[4])
			s += "O        ";
		else
			s += "-        ";
		switch(transmission)
		{
		case 1:
			s += "5-Manual";
			break;
		case 2:
			s += "6-Manual";
			break;
		case 3:
			s += "8-Auto";
			break;
		case 4:
			s +="Don't Care";
			break;
		}
		return s;		
	}
}
