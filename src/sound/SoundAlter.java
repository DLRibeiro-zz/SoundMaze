package sound;

import java.util.ArrayList;

public class SoundAlter {
	private ArrayList<SoundSource> soundSources;
	private ArrayList<ObjectiveSound> obj;
	public SoundAlter(ArrayList<ObjectiveSound> obj){
		this.obj = obj;
	}

	public void alter(String direction, String visao){
		if(visao.equals("N")){
			if(direction.equalsIgnoreCase("w")){
				for(ObjectiveSound s : this.obj){
					SoundSource x = s.getSource();
					x.y = x.y-1;
					//x.y = x.y+1;
					s.setObjectPosition(x.y, x.x, x.z);
				}
			}else if(direction.equalsIgnoreCase("s")){
				for(ObjectiveSound s : this.obj){
					SoundSource x = s.getSource();
					x.y = x.y+1;
					//x.y = x.y-1;
					s.setObjectPosition(x.y, x.x, x.z);
				}
			}
		}else if(visao.equals("S")){
			if(direction.equalsIgnoreCase("w")){
				for(ObjectiveSound s : this.obj){
					SoundSource x = s.getSource();
					//x.y = x.y+1;
					x.y = x.y-1;
					s.setObjectPosition(x.y, x.x, x.z);
				}
			}else if(direction.equalsIgnoreCase("s")){
				for(ObjectiveSound s : this.obj){
					SoundSource x = s.getSource();
					//x.y = x.y-1;
					x.y = x.y+1;
					s.setObjectPosition(x.y, x.x, x.z);
				}
			}
		}else if(visao.equals("O")){
			if(direction.equalsIgnoreCase("w")){
				for(ObjectiveSound s : this.obj){
					SoundSource x = s.getSource();
					x.y = x.y-1;
					//x.x = x.x+1;
					//x.x = x.x-1;
					s.setObjectPosition(x.y, x.x, x.z);
				}
			}else if(direction.equalsIgnoreCase("s")){
				for(ObjectiveSound s : this.obj){
					SoundSource x = s.getSource();
					x.y = x.y+1;
					//x.x = x.x-1;
					//x.x = x.x+1;
					s.setObjectPosition(x.y, x.x, x.z);
				}
			}
		}else if(visao.equals("L")){
			if(direction.equalsIgnoreCase("w")){
				for(ObjectiveSound s : this.obj){
					SoundSource x = s.getSource();
					x.y = x.y-1;
					//x.x = x.x-1;
					//x.x = x.x+1;
					s.setObjectPosition(x.y, x.x, x.z);
				}
			}else if(direction.equalsIgnoreCase("s")){
				for(ObjectiveSound s : this.obj){
					SoundSource x = s.getSource();
					x.y = x.y+1;
					//x.x = x.x+1;
					//x.x = x.x-1;
					s.setObjectPosition(x.y, x.x, x.z);
				}
			}
		}
		if(direction.equalsIgnoreCase("d")){
			for(ObjectiveSound s : this.obj){
				SoundSource x = s.getSource();
				Float antX = x.x;
				x.x = -x.y;
				//x.y = x.x;
				x.y = antX;
				s.setObjectPosition(x.y, x.x, x.z);
			}
		}else if(direction.equalsIgnoreCase("a")){
			for(ObjectiveSound s: this.obj){
				SoundSource x = s.getSource();
				Float antX = x.x;
				x.x = x.y;
				//x.y = -x.x;
				x.y = -antX;
				s.setObjectPosition(x.y, x.x, x.z);
			}
		}
	}

}
