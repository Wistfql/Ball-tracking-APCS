package Filters;

import Interfaces.Interactive;
import Interfaces.PixelFilter;
import core.DImage;

public class DetectBallsFilter implements PixelFilter, Interactive {
    int start_i = 0;
    int start_j = 0;
    int end_i = 0;
    int end_j = 0;

    int midpoint_i = 0;
    int midpoint_j = 0;

    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();




        // get midpoints [DO THIS MULTIPLE TIMES TO GET OPTIMAL RESULTS]

        for (int i = 0; i < red.length; i++) {
            for (int j = 0; j < red[i].length; j++) {

                if (red[i][j] == 255){

                        start_i = i;
                        start_j = j;
                        end_i = start_i;
                        end_j = start_j;
                        // loop to end of circle horizontally
                        while (red[end_i][end_j] != 0) {
                            end_i++;
                        }

                        // loop to end of circle vertically
                        while (red[end_i][end_j] != 0) {
                            end_j++;
                        }
                    midpoint_i = ((start_i+end_i)/2);
                    midpoint_j = ((end_j+start_j)/2);





                    System.out.println(midpoint_i + ", " + midpoint_j);
                    green[midpoint_i][midpoint_j] = 255;
                    red[midpoint_i][midpoint_j] = 0;
                    blue[midpoint_i][midpoint_j] = 0;



                }

            }

        }


        img.setColorChannels(red,green,blue);
        return img;
    }


    @Override
    public void mouseClicked(int mouseX, int mouseY, DImage img) {

    }

    @Override
    public void keyPressed(char key) {

    }


}
