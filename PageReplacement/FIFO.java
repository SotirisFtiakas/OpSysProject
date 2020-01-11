import java.util.ArrayList;

public class FIFO
{

    // Method to find page faults using indexes
    static int pageFaults(int pages[], int capacity)
    {
        ArrayList<Integer> memoryFrames = new ArrayList<>();
        int numberOfPages = pages.length;
        int faults=0;


        for(int i=0;i<numberOfPages;i++)
        {
            Integer page=pages[i];

            // If the page is not included in the memory frames
            if(memoryFrames.indexOf(page) == -1)
            {
                // If memory capacity exceeds, the first input element is deleted
                if(i>=capacity)
                    memoryFrames.remove(0);

                // We add the page at the end of the array,as of the last input element
                memoryFrames.add(page);
                faults++;
            }

            // If the page is included in the memory frames, nothing happens
        }

        return (faults);

    }

    // Driver Method to test your algorithm with a simple example
    public static void main(String args[])
    {
        /*
         * This is an array that holds the reference string for all
         * page requests.
         */
        int pages[] = {5, 1, 0, 3, 2, 3, 0, 4, 2, 3, 0, 3, 5, 2};

        // This is the number of available page frames
        int memoryCapacity = 3;

        int faults = pageFaults(pages, memoryCapacity);
        System.out.println(faults);
    }
}
